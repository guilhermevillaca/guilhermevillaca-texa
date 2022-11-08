package br.com.guilhermevillaca.utils;

import java.io.Serializable;
import java.util.List;

public class AwardInterval implements Serializable {

    private final List<ProducerWin> min;
    private final List<ProducerWin> max;

    public AwardInterval(List<ProducerWin> min, List<ProducerWin> max) {
        this.min = min;
        this.max = max;
    }

    public List<ProducerWin> getMin() {
        return min;
    }

    public List<ProducerWin> getMax() {
        return max;
    }

}
