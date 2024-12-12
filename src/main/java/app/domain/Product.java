package app.domain;

import java.util.Objects;

public class Product {
    private Long id;
    private String title;
    private double prise;
    private boolean active;

    public Product(String title, double prise) {
        this.title = title;
        this.prise = prise;
    }

    public Product(Long id, double prise) {
        this.id = id;
        this.prise = prise;
    }

    public Product(String title, double prise, boolean active) {
        this.title = title;
        this.prise = prise;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrise() {
        return prise;
    }

    public void setPrise(double prise) {
        this.prise = prise;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(prise, product.prise) == 0 && active == product.active && Objects.equals(id, product.id) && Objects.equals(title, product.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, prise, active);
    }

    @Override
    public String toString() {
        return String.format("Product: id - %d, title - %s, price - %.2f, active - %s.",
                id, title, prise, active ? "yes":"no");
    }
}
