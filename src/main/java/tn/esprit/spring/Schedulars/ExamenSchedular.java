package tn.esprit.spring.Schedulars;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.spring.Services.IExamenService;

@Component
@AllArgsConstructor
public class ExamenSchedular {
    IExamenService iExamenService;

    @Scheduled(fixedRate = 30000)
    public void method(){
        iExamenService.getAllTransactionByDate();
    }
}
