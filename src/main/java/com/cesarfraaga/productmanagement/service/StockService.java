package com.cesarfraaga.productmanagement.service;

import com.cesarfraaga.productmanagement.dto.ShoppingCartDTO;
import com.cesarfraaga.productmanagement.dto.StockDTO;
import com.cesarfraaga.productmanagement.entity.ShoppingCart;
import com.cesarfraaga.productmanagement.entity.Stock;
import com.cesarfraaga.productmanagement.exception.ResourceNotFoundException;
import com.cesarfraaga.productmanagement.repository.ShoppingCartRepository;
import com.cesarfraaga.productmanagement.repository.StockRepository;
import com.cesarfraaga.productmanagement.util.ShoppingCartMapper;
import com.cesarfraaga.productmanagement.util.StockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public StockDTO save(StockDTO stockDTO) {

        if (stockDTO.getId() == null)
            throw new IllegalArgumentException("Stock Id cannot be null or empty.");

        Stock stock = stockMapper.toEntityStock(stockDTO);
        stock = stockRepository.save(stock);
        return stockMapper.toDTOStock(stock);
    }

    public StockDTO update(StockDTO stockDTO) {
        if (stockDTO.getId() == null || !stockRepository.existsById(stockDTO.getId()))
            throw new ResourceNotFoundException("Stock not found with ID " + stockDTO.getId() + ".");

        Stock stock = stockMapper.toEntityStock(stockDTO);
        stock = stockRepository.save(stock);
        return stockMapper.toDTOStock(stock);
    }

    public StockDTO findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        Stock stock = stockRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Stock not found with ID " + id + "."));
        return stockMapper.toDTOStock(stock);
    }

    public void deleteById(Long id) {
        if (!stockRepository.existsById(id))
            throw new ResourceNotFoundException("Stock not found with ID " + id + ".");
        stockRepository.deleteById(id);
    }

    public List<StockDTO> findAll() {
        List<Stock> stockList = stockRepository.findAll();
        List<StockDTO> stockDTOList = new ArrayList<>();

        if (stockList.isEmpty())
            throw new ResourceNotFoundException("No stocks available.");

        for (Stock stock : stockList) {
            StockDTO stockDTO = stockMapper.toDTOStock(stock);
            stockDTOList.add(stockDTO);
        }
        return stockDTOList;
    }

}
