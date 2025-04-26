public class Main {
    public static void main(String[] args) {
        Pegawai pegawaiBiasa = new Pegawai("Andi", 5000000);
        Manager manager = new Manager("Budi", 10000000);
        Salesman salesman = new Salesman("Cici", 4000000, 50000000, 40000000);

        pegawaiBiasa.setNIP(1);
        manager.setNIP(2);
        salesman.setNIP(3);

        pegawaiBiasa.tambahCuti("pernikahan", "");
        manager.tambahCuti("persalinan", "pria");
        salesman.tambahCuti(5);

        System.out.println("\n=== Info Pegawai Biasa ===");
        pegawaiBiasa.tampilkanInfo();

        System.out.println("\n=== Info Manager ===");
        manager.tampilkanInfo();

        System.out.println("\n=== Info Salesman ===");
        salesman.tampilkanInfo();
    }
}
