package deu.cse.laundry;

interface WashStrategy {
    void wash();
    void rinse();
    void spin();
    int getTime();
}
