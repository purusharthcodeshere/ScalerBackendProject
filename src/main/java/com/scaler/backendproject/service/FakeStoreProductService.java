package com.scaler.backendproject.service;

import com.scaler.backendproject.dto.FakeStoreProductDTO;
import com.scaler.backendproject.exceptions.ProductNotFoundException;
import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//This annotation here is going to tell SpringBoot
//that this is one of our important class
//so create an object of this
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    //Inside this, fake store is going to be third party service

//    @Autowired
    private final RestTemplate restTemplate;
//    @Autowired
    private final RedisTemplate<String, Object> redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        System.out.println("We are inside the single product in FakeStoreProductService");

        //First part assume it as table name
        //Second part: key of the product
        Product redisProduct = (Product) redisTemplate
                .opsForHash()
                .get("PRODUCTS", "PRODUCTS_" + id);

        if (redisProduct != null) {
            //Cache Hit
            return redisProduct;
        }

        //If the earlier 'if' condition is not true
        //Then we have cache miss, and then we shall hit the api for FakeStore

        FakeStoreProductDTO fakeStoreProductDTO =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDTO.class);

//        System.out.println(fakeStoreProductDTO.toString());
        if (fakeStoreProductDTO == null) {
            throw new ProductNotFoundException("Product Not Found with id: " + id);
        }

        //Because we had cache miss, now we shall do cache eviction
        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCTS_" + id, fakeStoreProductDTO.getProduct());

        return fakeStoreProductDTO.getProduct();
    }


//    @Override
//    public Product[] getAllProducts(FakeStoreProductDTO[] listOfProducts) {
//        return new Product[0];
//    }

//    public List<Product> getAllProducts() throws ProductNotFoundException {
//        System.out.println("In the getAllProducts API in FKSPS");
//        FakeStoreProductDTO[] fakeStoreListOfProducts =
//                restTemplate.getForObject("https://fakestoreapi.com/products/",
//                        FakeStoreProductDTO[].class);
//        if (fakeStoreListOfProducts == null) {
//            throw new ProductNotFoundException("No Products Found in the Database");
//        }
//        return new FakeStoreProductDTO().getListOfProducts(fakeStoreListOfProducts);
//    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize, String fieldName) throws ProductNotFoundException {
        FakeStoreProductDTO[] fakeStoreListOfProducts =
                restTemplate.getForObject("https://fakestoreapi.com/products/",
                        FakeStoreProductDTO[].class);
        if (fakeStoreListOfProducts == null) {
            throw new ProductNotFoundException("No Products Found in the Database");
        }
        return (Page<Product>) new FakeStoreProductDTO().getListOfProducts(fakeStoreListOfProducts);
    }

    @Override
    public Product createProduct(Long id, String title, String description,
                                 Double price, String category, String imageUrl) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId(id);
        fakeStoreProductDTO.setTitle(title);
        fakeStoreProductDTO.setDescription(description);
        fakeStoreProductDTO.setPrice(price);
        fakeStoreProductDTO.setCategory(category);
        //Added this as Homework
        //So we had to change the FakeStoreProductService class
        //And since FakeStoreProductService implements ProductService Interface
        //We had to change that method signature as well
        fakeStoreProductDTO.setImage(imageUrl);

        FakeStoreProductDTO response = restTemplate.postForObject("https://fakestoreapi.com/products",
                fakeStoreProductDTO, FakeStoreProductDTO.class);

        return response.getProduct();
    }

    @Override
    public Product deleteProduct(Long id) throws ProductNotFoundException {
        System.out.println("Inside the delete product in FakeStoreProductService API");
        //No return type, as the restTemplate returns void for delete function
        //So, we first store the product in a temp variable
        //To show which product we are deleting
        Product deletedProduct = getSingleProduct(id);
        deletedProduct.setDeleted(true);
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
        return deletedProduct;
    }


    public Product updateProduct(Long id, String title, String description,
                                 Double price, Category category, String imageUrl) throws ProductNotFoundException {
        System.out.println("Inside the update product in FakeStoreProductService API");
        Product existingProduct = getSingleProduct(id);

        ResponseEntity<Product> responseEntity = null;
        try {
            System.out.println("Updating the Product");
            if (title != null) {
                existingProduct.setTitle(title);
            }
            if (description != null) {
                existingProduct.setDescription(description);
            }
            if (price != null) {
                existingProduct.setPrice(price);
            }
            if (category != null) {
                existingProduct.setCategory(category);
            }
            if (imageUrl != null) {
                existingProduct.setImageUrl(imageUrl);
            }

            // Create HttpEntity with the updated product
            HttpEntity<Product> requestEntity = new HttpEntity<>(existingProduct);

            // Use exchange() method instead of patchForObject
            responseEntity = restTemplate.exchange(
                    "https://fakestoreapi.com/products/" + id,
                    HttpMethod.PUT,
                    requestEntity,
                    Product.class
            );
        } catch (RuntimeException re) {
            throw new RuntimeException("Product Not Found" + re);
        }

        return responseEntity.getBody(); //responseEntity != null ? responseEntity.getBody() : null;
    }

//    public Product updateProduct(Long id, String title, String description,
//                                 Double price, Category category, String imageUrl) {
//        System.out.println("Inside the update product in FakeStoreProductService API");
//        Product existingProduct = getSingleProduct(id);
//
//        if (existingProduct != null) {
//            FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
//            System.out.println("Updating the Product");
//            fakeStoreProductDTO.setId(id);
//            if (title != null) {
//                existingProduct.setTitle(title);
//            }
//            if (description != null) {
//                existingProduct.setDescription(description);
//            }
//            if (price != null) {
//                existingProduct.setPrice(price);
//            }
//            if (category != null) {
//                existingProduct.setCategory(category);
//            }
//            if (imageUrl != null) {
//                existingProduct.setImageUrl(imageUrl);
//            }
//            Product response =
//                    restTemplate.patchForObject("https://fakestoreapi.com/products/" + id,
//                            existingProduct, Product.class);
//            return response;
//        } else {
//            throw new RuntimeException("Product Not Found");
//        }
//       return null;
//    }

}
