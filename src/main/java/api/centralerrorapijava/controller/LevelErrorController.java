package api.centralerrorapijava.controller;

import api.centralerrorapijava.model.LevelError;
import api.centralerrorapijava.service.LevelErrorServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/levels")
public class LevelErrorController {
    @Autowired
    private LevelErrorServiceImpl levelErrorService;

    @GetMapping
    @ApiOperation("List all errors level")
    public ResponseEntity<List<LevelError>> find() {
       return new ResponseEntity<>(this.levelErrorService.findAll(), HttpStatus.OK);
    }
}
