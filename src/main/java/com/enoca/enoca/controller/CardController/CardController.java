package com.enoca.enoca.controller.CardController;

import com.enoca.enoca.model.Card;
import com.enoca.enoca.model.Product;
import com.enoca.enoca.service.DTO.Product.ProductDto;
import com.enoca.enoca.service.abtracts.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/card")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/id/{customerId}")
    public ResponseEntity getCart(@PathVariable String customerId) {
        Card card = cardService.getCart(customerId);
        return ResponseEntity.ok(card);

    }
    @DeleteMapping("/id/{customerId}")
    public ResponseEntity emptyCart( @PathVariable String customerId){
        cardService.emptyCart(customerId);
        return ResponseEntity.ok("Card items deleted successfully");

    }
    @PutMapping("/id/{customerId}/update")
    public ResponseEntity updateCart(@PathVariable String customerId
            ,@RequestBody ProductDto productDto
            ,@RequestParam int quantity) {
        cardService.updateCart(customerId,productDto,quantity);
        return ResponseEntity.ok("Card items updated successfully");
    }
    @PostMapping("/id/{customerId}/add")
    public ResponseEntity<String> addProductToCart(@PathVariable String customerId,
                                                   @RequestBody ProductDto productDto,
                                                   @RequestParam int quantity) {
        cardService.addProductToCart(customerId, productDto, quantity);
        return ResponseEntity.ok("Product added to cart successfully");
    }

    @DeleteMapping("/id/{customerId}/remove")
    public ResponseEntity<String> removeProductFromCart(@PathVariable String customerId,
                                                        @RequestBody ProductDto productDto) {
        cardService.removeProductFromCart(customerId, productDto);
        return ResponseEntity.ok("Product removed from cart successfully");
    }

}
