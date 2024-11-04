package com.enoca.enoca.service.concreate;

import com.enoca.enoca.core.exceptions.ProductNotFoundException;
import com.enoca.enoca.core.modelMapper.ModelMapperService;
import com.enoca.enoca.model.Card;
import com.enoca.enoca.model.CardItem;
import com.enoca.enoca.model.Customer;
import com.enoca.enoca.model.Product;
import com.enoca.enoca.repository.CardRepository;
import com.enoca.enoca.repository.CustomerRepository;
import com.enoca.enoca.repository.ProductRepository;
import com.enoca.enoca.service.DTO.Product.ProductDto;
import com.enoca.enoca.service.abtracts.CardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CardManager implements CardService {

    private final CardRepository cardRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapperService modelMapperService;
    private final ProductRepository productRepository;

    public CardManager(CardRepository cardRepository,ModelMapperService modelMapperService, CustomerRepository customerRepository, ProductRepository productRepository) {

        this.cardRepository = cardRepository;
        this.customerRepository = customerRepository;
        this.modelMapperService = modelMapperService;
        this.productRepository = productRepository;
    }


    @Override
    public Card getCart(String customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty() || customer.get().getCard() == null) {
            throw new NoSuchElementException("No cart found for customer with ID: " + customerId);
        }
        return customer.get().getCard();
    }

    @Override
    @Transactional
    public void emptyCart(String customerId) {
        Card card = getCart(customerId);

        card.getCartItems().clear();      // Sepetteki tüm öğeleri temizle
        card.setTotalPrice(BigDecimal.ZERO); // Toplam fiyatı sıfırla

        cardRepository.save(card);
    }

    @Override
    public void updateCart(String customerId, ProductDto productDto, int quantity) {

        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        Card card = getCart(customerId);

        // Ürün sepette varsa
        Optional<CardItem> existingItem = card.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CardItem item = existingItem.get();

            if (quantity == 0) {
                // Miktar 0 ise ürünü sepetten çıkar
                card.getCartItems().remove(item);
            } else {
                // Ürünün miktarını güncelle
                item.setQuantity(quantity);
                item.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
            }
        } else if (quantity > 0) {
            // Ürün sepette yoksa ve miktar 0'dan büyükse, yeni bir CardItem olarak ekle
            CardItem newItem = new CardItem();
            newItem.setCard(card);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
            card.getCartItems().add(newItem);
        }

        // Toplam fiyatı güncelle
        updateTotalPrice(card);
        cardRepository.save(card);
    }

    public void addProductToCart(String customerId, ProductDto productDto, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero to add a product.");
        }
        // Ürün ID ile ürünü veritabanından getir, yoksa hata fırlat
        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productDto.getId()));
        // Müşteriye ait sepeti al
        Card card = getCart(customerId);

        // Sepette ilgili ürün var mı kontrol et
        Optional<CardItem> existingItem = card.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            // Ürün sepette varsa mevcut miktarı artır ve toplam fiyatı güncelle
            CardItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            item.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        } else {
            // Ürün sepette yoksa yeni bir CardItem oluştur ve sepete ekle
            CardItem newItem = new CardItem();
            newItem.setCard(card);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
            card.getCartItems().add(newItem);
        }

        // Sepetin toplam fiyatını güncelle ve sepeti kaydet
        updateTotalPrice(card);
        cardRepository.save(card);
    }
    public void removeProductFromCart(String customerId, ProductDto productDto) {
        // Müşteriye ait sepeti al
        Card card = getCart(customerId);

        // Ürün ID ile sepetteki ürünü bul
        Optional<CardItem> itemToRemove = card.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productDto.getId()))
                .findFirst();

        if (itemToRemove.isPresent()) {
            // Ürünü sepetten kaldır
            card.getCartItems().remove(itemToRemove.get());

            // Toplam fiyatı güncelle ve sepeti kaydet
            updateTotalPrice(card);
            cardRepository.save(card);
        } else {
            throw new NoSuchElementException("Product not found in the cart with ID: " + productDto.getId());
        }
    }

    private void updateTotalPrice(Card card) {
        BigDecimal total = card.getCartItems().stream()
                .map(CardItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        card.setTotalPrice(total);
    }
}
