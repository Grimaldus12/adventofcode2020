package day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ticket {

    List<Long> parameters = new ArrayList<>();

    public void addParameter(long value) { parameters.add(value); }
    public int getParameterSize() {return parameters.size();}

    public long getParameter(int i) {
        return parameters.get(i);
    }
}
