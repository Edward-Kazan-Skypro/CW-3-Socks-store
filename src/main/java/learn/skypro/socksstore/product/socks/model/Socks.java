package learn.skypro.socksstore.product.socks.model;

import lombok.Data;

@Data
//Убрал эту аннотацию - с ней не могу сделать проверку в конструкторе
//@AllArgsConstructor
public class Socks {

    private int cottonPart;
    private SocksColor socksColor;
    private SocksSize socksSize;
    private boolean inStock;

    public Socks(int cottonPart, SocksColor socksColor, SocksSize socksSize) {
        if (cottonPart > 0 && cottonPart <= 100) {
            this.cottonPart = cottonPart;
        } else {
            throw new IllegalArgumentException("% содержания хлопка указан как " + cottonPart + "%");
        }
        this.socksColor = socksColor;
        this.socksSize = socksSize;
    }
}
