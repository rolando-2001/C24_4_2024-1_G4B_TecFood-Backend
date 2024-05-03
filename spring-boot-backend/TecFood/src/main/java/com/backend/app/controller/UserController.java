package com.backend.app.controller;

import com.backend.app.dto.UserDto;
import com.backend.app.exception.MensajeResponse;
import com.backend.app.service.IUserService;
import com.backend.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {


    @Autowired
    private IUserService userService;

    @GetMapping("users")
    public ResponseEntity<?> showAll(){
        List<User> getlist= userService.allUser();
        if(getlist==null){
            return  new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No hay registros aun")
                    .object(null)
                    .build(),HttpStatus.OK);

        }
        return  new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(getlist)
                        .build()
                ,HttpStatus.OK);

    }


    //Create

    @PostMapping("user")
    public ResponseEntity<?> create(@RequestBody UserDto userDto){
        User userSave =null;
        try{
            userSave=userService.save(userDto);
            userDto= UserDto.builder()
                    .id(userSave.getId())
                    .firstName(userSave.getFirstName())
                    .lastName(userSave.getLastName())
                    .phoneNumber(userSave.getPhoneNumber())
                    .email(userSave.getEmail())
                    .password(userSave.getPassword())
                    .dni(userSave.getDni())
                    .imgUrl(userSave.getImgUrl())
                    .isGoogleAccount(userSave.isGoogleAccount())
                    .isVerifiedEmail(userSave.isVerifiedEmail())
                    .roleId(userDto.getRoleId())
                    .build();
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(userDto)
                    .build()
                     ,HttpStatus.CREATED);
        }catch (DataAccessException exDt){
            return  new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .object(null)
                    .build(),HttpStatus.METHOD_NOT_ALLOWED);
        }


    }



    @PutMapping("user/{id}")
    @ResponseStatus(HttpStatus.CREATED)
   public ResponseEntity<?> update(@RequestBody UserDto userDto,@PathVariable Integer id){

       User userUpdate =null;
       try{
          if (userService.existsById(id)){
              userDto.setId(id);
              userUpdate=userService.save(userDto);
              userDto =UserDto.builder()
                      .id(userUpdate.getId())
                      .firstName(userUpdate.getFirstName())
                      .lastName(userUpdate.getLastName())
                      .phoneNumber(userUpdate.getPhoneNumber())
                      .email(userUpdate.getEmail())
                      .password(userUpdate.getPassword())
                      .dni(userUpdate.getDni())
                      .imgUrl(userUpdate.getImgUrl())
                      .isGoogleAccount(userUpdate.isGoogleAccount())
                      .isVerifiedEmail(userUpdate.isVerifiedEmail())
                      .roleId(userDto.getRoleId())
                      .build();
              return  new ResponseEntity<>(MensajeResponse.builder()
                      .mensaje("Actualizado correctamente")
                      .object(userDto)
                      .build(),
                      HttpStatus.CREATED);

          }else{
              return  new ResponseEntity<>(MensajeResponse.builder()
                      .mensaje("El usuario no existe en la base de datos")
                      .object(null)
                      .build(),HttpStatus.NOT_FOUND);

          }

       }catch (DataAccessException exDt){
           return  new ResponseEntity<>(MensajeResponse.builder()
                   .mensaje(exDt.getMessage())
                   .object(null)
                   .build(),HttpStatus.METHOD_NOT_ALLOWED);

       }

   }


   @DeleteMapping("user/{id}")
   public ResponseEntity<?> delete(@PathVariable Integer id ){
        try{
            User userDelete=userService.findById(id);
            userService.delete(userDelete);
            //return ResponseEntity.noContent().build();
            return  new ResponseEntity<>(userDelete,HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDt){
            return  new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .object(null)
                    .build(),HttpStatus.INTERNAL_SERVER_ERROR);

        }



   }




    @GetMapping("user/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id){
        User user=userService.findById(id);
        if(user==null){
            return  new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El Usuario no existe")
                    .object(null)
                    .build(),HttpStatus.NOT_FOUND);

        }
        return  new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(UserDto.builder()
                                .id(user.getId())
                                .firstName(user.getFirstName())
                                .lastName(user.getLastName())
                                .phoneNumber(user.getPhoneNumber())
                                .email(user.getEmail())
                                .password(user.getPassword())
                                .dni(user.getDni())
                                .imgUrl(user.getImgUrl())
                                .isGoogleAccount(user.isGoogleAccount())
                                .isVerifiedEmail(user.isVerifiedEmail())
                                .roleId(user.getRoleId())
                                .build())
                        .build()
                ,HttpStatus.OK);

    }

}
