package diplom.server.controller

import diplom.server.dto.UserDto
import diplom.server.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController (
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder
) {

    @GetMapping
    fun getAll(): List<UserDto> = userService.getAll()

    @PostMapping("/reg")
    fun create(@RequestBody dto: UserDto): Int {
        // Хэшируем пароль перед сохранением
        val hashedPassword = passwordEncoder.encode(dto.password)
        // Создаем новый объект UserDto с хэшированным паролем
        val userDtoWithHashedPassword = UserDto(
            user_name = dto.user_name,
            email = dto.email,
            password = hashedPassword
        )
        return userService.create(userDtoWithHashedPassword)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: UserDto): ResponseEntity<Any> {
        val user = userService.findByEmail(request.email)
        if (user != null && passwordEncoder.matches(request.password, user.password)) {
            // Пользователь найден и пароль совпадает
            // Возвращаем успех
            return ResponseEntity.ok().build()
        } else {
            // Пользователь не найден или пароль не совпадает
            // Возвращаем ошибку
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }

}