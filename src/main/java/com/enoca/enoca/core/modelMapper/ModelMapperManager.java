package com.enoca.enoca.core.modelMapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;


    @Service
    // ben her seferinde yeni bir bir ModelMapper uretilmesini istemiyoeum bunu IOC yerlesmesini istiyorum ondan dolayı service anatasyonun koyuyorum
    @AllArgsConstructor
    public class ModelMapperManager implements ModelMapperService {
        private ModelMapper modelMapper;

        @Override
        public ModelMapper forResponse() { // gevsek mapleme yap
            this.modelMapper.getConfiguration() // yani veri tabanında id , name,x,y,z gibi bir suru alan var ama sen diyorsun ki response nesnemde
                    .setAmbiguityIgnored(true)            // sadece name,x,y varsa onları maple diğerleri maplanırse engel olusturma
                    .setMatchingStrategy(MatchingStrategies.LOOSE);
            return this.modelMapper;
        }

        @Override
        public ModelMapper forRequest() {                // request aldığımda ise hersey maplansin (demekki programci iki tarafta isimlendirmede hata yaptı )
            this.modelMapper.getConfiguration()
                    .setAmbiguityIgnored(true)
                    .setMatchingStrategy(MatchingStrategies.STANDARD);
            return this.modelMapper;
        }
    }
