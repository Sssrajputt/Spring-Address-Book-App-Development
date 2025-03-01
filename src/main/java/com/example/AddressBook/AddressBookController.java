package com.example.AddressBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressbookservice")
public class AddressBookController {

    @Autowired
    private IAddressBookService addressBookService;

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAddressBookData() {
        List<AddressBook> addressBookList = addressBookService.getAddressBookData();
        ResponseDTO respDTO = new ResponseDTO("Get Call Successful", addressBookList.toString());
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getAddressBookData(@PathVariable("id") Long id) {
        AddressBook addressBook = addressBookService.getAddressBookDataById(id);
        ResponseDTO respDTO = new ResponseDTO("Get Call For ID Successful", addressBook.toString());
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAddressBookData(@RequestBody AddressBookDTO addressBookDTO) {
        AddressBook newAddressBook = addressBookService.createAddressBookData(addressBookDTO);
        ResponseDTO respDTO = new ResponseDTO("Created Address Book Data Successfully", newAddressBook.toString());
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateAddressBookData(@PathVariable("id") Long id, @RequestBody AddressBookDTO addressBookDTO) {
        AddressBook updatedAddressBook = addressBookService.updateAddressBookData(id, addressBookDTO);
        ResponseDTO respDTO = new ResponseDTO("Updated Address Book Data Successfully", updatedAddressBook.toString());
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteAddressBookData(@PathVariable("id") Long id) {
        addressBookService.deleteAddressBookData(id);
        ResponseDTO respDTO = new ResponseDTO("Deleted Successfully", "Deleted id: " + id);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
}
