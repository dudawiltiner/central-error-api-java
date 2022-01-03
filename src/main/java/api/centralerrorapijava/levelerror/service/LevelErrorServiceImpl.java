package api.centralerrorapijava.levelerror.service;


import api.centralerrorapijava.levelerror.model.LevelError;
import api.centralerrorapijava.levelerror.repository.LevelErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelErrorServiceImpl {
        @Autowired
        private LevelErrorRepository levelerrorRepository;

        public List<LevelError> findAll() {
            return this.levelerrorRepository.findAll();
        }

        public List<LevelError> save() {
            return this.levelerrorRepository.findAll();
        }
}
