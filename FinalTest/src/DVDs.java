public class DVDs extends Items {
    // Field variable to represent the playing time of the DVD.
    // The unit of playback time is seconds.
    private int playingTime;

    DVDs(String name, double price, int playingTime) {
        super(name, price);
        this.playingTime = playingTime;
    }
    DVDs(String name, double price, double discountPercentage, int playingTime) {
        super(name, price, discountPercentage);
        this.playingTime = playingTime;
    }

    public int getPlayingTime() {
        return this.playingTime;
    }

    public void setPlayingTime(int playingTime) {
        this.playingTime = playingTime;
    }

    @Override
    public String toString() {
        // Print playing time as xx:xx format.
        return super.toString() + "Playing Time: " + String.format("%02d", this.playingTime / 60) + ":" + String.format("%02d", this.playingTime % 60) + "\n";
    }
}