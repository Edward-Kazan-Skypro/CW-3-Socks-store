package learn.skypro.socksstore.product.socks.model;

public enum SocksColor {
    BLACK ("черный"),
    WHITE ("белый"),
    RED ("красный"),
    YELLOW ("желтый"),
    BLUE ("синий"),
    BROWN ("коричневый");

    private final String color;

    SocksColor(String color) {
        this.color = color;
    }
}
