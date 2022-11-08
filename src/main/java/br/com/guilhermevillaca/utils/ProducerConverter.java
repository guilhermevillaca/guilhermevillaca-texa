package br.com.guilhermevillaca.utils;


import br.com.guilhermevillaca.utils.AwardInterval;
import br.com.guilhermevillaca.utils.ProducerWin;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProducerConverter {

    public void updateMinAwardsRange(List<ProducerWin> minRange, AwardInterval awardInterval) {
        awardInterval.getMin().forEach(producerInterval -> {
            ProducerWin record = new ProducerWin();
            record.setFollowingWin(producerInterval.getFollowingWin());
            record.setInterval(producerInterval.getInterval());
            record.setPreviousWin(producerInterval.getPreviousWin());
            record.setProducer(producerInterval.getProducer());
            minRange.add(record);
        });
    }

    public void updateMaxAwardsRange(List<ProducerWin> maxRange, AwardInterval awardInterval) {
        awardInterval.getMax().forEach(producerInterval -> {
            ProducerWin record = new ProducerWin();
            record.setFollowingWin(producerInterval.getFollowingWin());
            record.setInterval(producerInterval.getInterval());
            record.setPreviousWin(producerInterval.getPreviousWin());
            record.setProducer(producerInterval.getProducer());
            maxRange.add(record);
        });
    }

}
