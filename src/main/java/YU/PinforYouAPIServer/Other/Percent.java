package YU.PinforYouAPIServer.Other;

public class Percent {

    static Double[] percent = new Double[6];
    static Integer[] card_ids = new Integer[6];
    static Integer idx = 0;
    public Double set_card(int card_id) {
        card_ids[idx] = card_id;
        return percent[idx++];
    }

    public Double get_percent(int card_id) {
        for(int i = 0; i < 6; i++)
            if(card_ids[i] == card_id)
                return percent[i];
        return 1.00;
    }

    public void reset() {
        card_ids = new Integer[6];
        percent[0] = 0.90;
        percent[1] = 0.93;
        percent[2] = 0.95;
        percent[3] = 0.97;
        percent[4] = 0.98;
        percent[5] = 0.99;

        idx = 0;
    }
}
