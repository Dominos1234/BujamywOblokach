package com.backend.api.controller
import com.backend.api.model.Champion
import com.backend.api.repo.ChampionsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.jpa.repository.Query
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import kotlin.NoSuchElementException

@RestController
@RequestMapping("api")
class ApiController {

    @Autowired
    lateinit var repository: ChampionsRepository

    @GetMapping("champions")
    fun getChampions() = repository.findAll()

    @GetMapping("champion")
    fun getChampionByName(@RequestParam(required = false) id: Long?, @RequestParam(required = false) name: String?) : ResponseEntity<Optional<Champion>>{
        if (id != null && name != null) {
            return checkIfNull(repository.findByIdAndName(id, name))
        } else if (id != null) {
            return checkIfNull(repository.findById(id))
        } else if (name != null) {
            return checkIfNull(repository.findByName(name))
        }
        return checkIfNull(Optional.empty())
    }

    @PutMapping("champion", produces = ["application/json"])
    @ResponseBody
    fun addChampion(@RequestBody champion: Champion) : ResponseEntity<Any> {
        try {
            repository.save(champion)
            return ResponseEntity("", HttpStatus.OK)
        } catch (e: DataIntegrityViolationException) {
            return ResponseEntity("{\"error\":\"Record already exists\"}", HttpStatus.CONFLICT)
        }

    }

    @PostMapping("champion", produces = ["application/json"])
    @ResponseBody
    fun editChampion(@RequestBody editedChampion: Champion) : ResponseEntity<Any> {
        try {
            val champion: Champion = repository.findById(editedChampion.id).get()
            repository.save(editedChampion)
            return ResponseEntity("", HttpStatus.OK)
        } catch (e: DataIntegrityViolationException) {
            return ResponseEntity("{\"error\":\"Name already exists\"}", HttpStatus.CONFLICT)
        } catch (e: NoSuchElementException) {
            return ResponseEntity("{\"error\":\"ID does not exist\"}", HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("stats", produces = ["application/json"])
    @ResponseBody
    fun getStats() : ResponseEntity<Any>{
        val query = repository.getStats()
        return ResponseEntity("{" +
                "\"avg_hp\" : ${query.avg_hp}," +
                "\"avg_hp_plus\" : ${query.avg_hp_plus}," +
                "\"avg_hp5\" : ${query.avg_hp5}," +
                "\"avg_hp5_plus\" : ${query.avg_hp5_plus}," +
                "\"avg_mp\" : ${query.avg_mp}," +
                "\"avg_mp_plus\" : ${query.avg_mp_plus}," +
                "\"avg_mp5\" : ${query.avg_mp5}," +
                "\"avg_mp5_plus\" : ${query.avg_mp5_plus}," +
                "}", HttpStatus.OK)
    }

    fun checkIfNull(champion: Optional<Champion>) : ResponseEntity<Optional<Champion>> {
        return if (champion.isPresent){
            ResponseEntity(champion, HttpStatus.OK)
        } else {
            ResponseEntity(Optional.empty(), HttpStatus.NOT_FOUND)
        }
    }

}