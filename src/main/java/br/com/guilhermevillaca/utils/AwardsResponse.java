package br.com.guilhermevillaca.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AwardsResponse implements Serializable {

    private List<ProducerWin> min;
    private List<ProducerWin> max;

    public AwardsResponse() {
    }

    public List<ProducerWin> getMin() {
        if (min == null) {
            min = new ArrayList<>();
        }
        return min;
    }

    public List<ProducerWin> getMax() {
        if (max == null) {
            max = new ArrayList<>();
        }
        return max;
    }
    
    

}
