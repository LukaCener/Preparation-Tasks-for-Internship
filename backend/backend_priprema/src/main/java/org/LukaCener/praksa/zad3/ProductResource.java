package org.LukaCener.praksa.zad3;

import io.quarkus.panache.common.Sort;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/products")
@Produces("application/json")
@Consumes("application/json")
public class ProductResource {

    @GET
    public List<Product> getProducts(
            @QueryParam("minPrice") Double minPrice,
            @QueryParam("maxPrice") Double maxPrice,
            @QueryParam("category") String category,
            @QueryParam("sort") String sort
    ) {

        Sort sortBy = Sort.empty();
        if ("name".equals(sort)) {
            sortBy = Sort.by("name");
        } else if ("price".equals(sort)) {
            sortBy = Sort.by("price");
        }

        if (category != null && minPrice != null && maxPrice != null) {
            return Product.find(
                    "category = ?1 and price >= ?2 and price <= ?3",
                    sortBy,
                    category, minPrice, maxPrice
            ).list();
        }

        if (category != null) {
            return Product.find("category", sortBy, category).list();
        }

        if (minPrice != null && maxPrice != null) {
            return Product.find(
                    "price >= ?1 and price <= ?2",
                    sortBy,
                    minPrice, maxPrice
            ).list();
        }

        return Product.findAll(sortBy).list();
    }

    @POST
    @Transactional
    public Response addProduct(Product product) {
        product.persist();
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteProduct(@PathParam("id") Long id) {
        Product product = Product.findById(id);
        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        product.delete();
        return Response.noContent().build();
    }
}