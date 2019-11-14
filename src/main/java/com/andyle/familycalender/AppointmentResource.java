package com.andyle.familycalender;

import io.quarkus.panache.common.Sort;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;

@Path("appointments")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class AppointmentResource {

    @GET
    public List<Appointment> get() {
        return Appointment.listAll(Sort.by("name"));
    }

    @GET
    @Path("{id}")
    public Appointment getSingle(@PathParam Long id) {
        Appointment entity = Appointment.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Appointment with id of " + id + " does not exist.", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(Appointment appointment) {
        if (appointment.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        appointment.persist();
        return Response.ok(appointment).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Appointment update(@PathParam Long id, Appointment appointment) {
        if (appointment.title == null) {
            throw new WebApplicationException("Appointment Name was not set on request.", 422);
        }

        Appointment entity = Appointment.findById(id);
        

        if (entity == null) {
            throw new WebApplicationException("Appointment with id of " + id + " does not exist.", 404);
        }

        entity.title = appointment.title;

        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Long id) {
        Appointment entity = Appointment.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Appointment with id of " + id + " does not exist.", 404);
        }
        entity.delete();
        return Response.status(204).build();
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Override
        public Response toResponse(Exception exception) {
            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }
            return Response.status(code)
                    .entity(Json.createObjectBuilder().add("error", exception.getMessage()).add("code", code).build())
                    .build();
        }

    }
}
