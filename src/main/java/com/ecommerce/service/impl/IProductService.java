package com.ecommerce.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ecommerce.converter.ProductConverter;
import com.ecommerce.entity.*;
import com.ecommerce.model.dto.*;
import com.ecommerce.model.response.ProductResponse;
import com.ecommerce.repository.AttributeDetailRepository;
import com.ecommerce.repository.AttributeRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IProductService implements ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final AttributeRepository attributeRepository;
    private final AttributeDetailRepository attributeDetailRepository;
    private final Cloudinary cloudinary;

    @Override
    @Transactional
    public ProductDTO addOrUpdateProduct(ProductAddDTO productAddDTO) {
        ProductEntity productEntity = productConverter.fromAddDTOToEntity(productAddDTO);
        List<ProductImageEntity> images = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<ProductImageEntity>> futures = new ArrayList<>();

        AtomicBoolean isMain = new AtomicBoolean(true);
        // Upload hình ảnh song song
        for (MultipartFile file : productAddDTO.getImages()) {
            futures.add(executorService.submit(() -> {
                try {
                    Map up = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                    String urlImage = (String) up.get("secure_url");

                    ProductImageEntity imageEntity = new ProductImageEntity();
                    imageEntity.setUrl(urlImage);
                    imageEntity.setProduct(productEntity);
                    imageEntity.setMain(isMain.getAndSet(false));  // Chỉ đặt ảnh chính lần đầu

                    return imageEntity;
                } catch (Exception e) {
                    throw new RuntimeException("Failed to upload image to Cloudinary", e);
                }
            }));
        }

        // Lấy kết quả từ các luồng
        for (Future<ProductImageEntity> future : futures) {
            try {
                ProductImageEntity imageEntity = future.get();  // Chờ kết quả từ luồng
                images.add(imageEntity);
            } catch (Exception e) {
                // Log lỗi hoặc xử lý lỗi nếu cần
                System.err.println("Error while processing image upload: " + e.getMessage());
            }
        }
        executorService.shutdown();

        // Lưu thông tin sản phẩm vào cơ sở dữ liệu
        productEntity.setProductImageEntityList(images);
        productRepository.save(productEntity);

        return productConverter.fromEntityToDTO(productEntity);
    }

    @Override
    public ProductResponse getAllProducts() {
        ProductResponse response = new ProductResponse();
        response.setProducts(productRepository.findAll().stream().map(productConverter::fromEntityToDTO).collect(Collectors.toList()));
        return response ;
    }

    @Override
    public ProductResponse searchProducts(ProductSearchCriteria criteria) {
        List<ProductEntity> entityList = productRepository.searchProduct(criteria);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProducts(entityList.stream().map(productConverter::fromEntityToDTO).collect(Collectors.toList()));
        productResponse.setPage(criteria.getPage());
        productResponse.setLimit(criteria.getLimit());
        productResponse.setTotal(productRepository.countProducts(criteria));
        return productResponse;
    }

    @Override
    public ProductResponse searchProductsByAdmin(ProductSearchCriteriaAdmin criteria) {
        List<ProductEntity> entityList = productRepository.searchProductAdmin(criteria);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProducts(entityList.stream().map(productConverter::fromEntityToDTO).collect(Collectors.toList()));
        productResponse.setPage(criteria.getPage());
        productResponse.setLimit(criteria.getLimit());
        productResponse.setTotal(productRepository.countProductsSearchAdmin(criteria));
        return productResponse;
    }

    @Override
    public ProductDTO getProductById(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return productConverter.fromEntityToDTO(productEntity);
    }
}
