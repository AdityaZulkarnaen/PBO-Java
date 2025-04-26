public class Salesman extends Pegawai {
    private double penjualan;
    private double target;

    public Salesman(String nama, double gaji, double penjualan, double target) {
        super(nama, gaji);
        this.penjualan = penjualan;
        this.target = target;
    }

    @Override
    public double hitungTHR() {
        if (penjualan >= target) {
            return 2 * gaji;
        } else {
            return gaji;
        }
    }

    @Override
    public void setNIP(String nipInput) {
        if (nipInput.startsWith("S")) {
            this.nip = nipInput;
        } else {
            System.out.println("Format NIP tidak sesuai untuk Salesman.");
        }
    }

    @Override
    public void setNIP(int urutanMasuk) {
        this.nip = "S" + urutanMasuk;
    }
}
