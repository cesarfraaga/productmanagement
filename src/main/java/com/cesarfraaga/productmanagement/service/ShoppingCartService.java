package com.cesarfraaga.productmanagement.service;

import com.cesarfraaga.productmanagement.dto.ShoppingCartDTO;
import com.cesarfraaga.productmanagement.entity.ShoppingCart;
import com.cesarfraaga.productmanagement.exception.ResourceNotFoundException;
import com.cesarfraaga.productmanagement.repository.ShoppingCartRepository;
import com.cesarfraaga.productmanagement.util.ShoppingCartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    public ShoppingCartDTO save(ShoppingCartDTO shoppingCartDTO) {

        /*if (shoppingCartDTO.getProductsIds() == null)
            throw new IllegalArgumentException("Product Id cannot be null or empty.");
         */
        ShoppingCart shoppingCart = shoppingCartMapper.toEntityShoppingCart(shoppingCartDTO);
        shoppingCart = shoppingCartRepository.save(shoppingCart);
        return shoppingCartMapper.toDTOShoppingCart(shoppingCart);
    }

    public ShoppingCartDTO update(ShoppingCartDTO shoppingCartDTO) {
        if (shoppingCartDTO.getId() == null || !shoppingCartRepository.existsById(shoppingCartDTO.getId()))
            throw new ResourceNotFoundException("Shopping Cart not found with ID " + shoppingCartDTO.getId() + ".");

        /*if (shoppingCartDTO.getProductsIds().isEmpty())
            throw new ResourceNotFoundException("Shopping Cart is empty.");
         */
        ShoppingCart shoppingCart = shoppingCartMapper.toEntityShoppingCart(shoppingCartDTO);
        shoppingCart = shoppingCartRepository.save(shoppingCart);
        return shoppingCartMapper.toDTOShoppingCart(shoppingCart);
    }

    public ShoppingCartDTO findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Shopping Cart not found with ID " + id + "."));
        return shoppingCartMapper.toDTOShoppingCart(shoppingCart);
    }

    public void deleteById(Long id) {
        if (!shoppingCartRepository.existsById(id))
            throw new ResourceNotFoundException("Shopping Cart not found with ID " + id + ".");
        shoppingCartRepository.deleteById(id);
    }

    public List<ShoppingCartDTO> findAll() {
        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAll();
        List<ShoppingCartDTO> shoppingCartDTOList = new ArrayList<>();

        if (shoppingCartList.isEmpty())
            throw new ResourceNotFoundException("No shopping carts available.");

        for (ShoppingCart shoppingCart : shoppingCartList) {
            ShoppingCartDTO shoppingCartDTO = shoppingCartMapper.toDTOShoppingCart(shoppingCart);
            shoppingCartDTOList.add(shoppingCartDTO);
        }
        return shoppingCartDTOList;
    }

}

