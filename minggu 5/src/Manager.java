public class Manager extends Pegawai {
    public Manager(String nama, double gaji) {
        super(nama, gaji);
    }

    @Override
    public double hitungTHR() {
        return 2 * gaji;
    }

    @Override
    public void setNIP(String nipInput) {
        if (nipInput.startsWith("M")) {
            this.nip = nipInput;
        } else {
            System.out.println("Format NIP tidak sesuai untuk Manager.");
        }
    }

    @Override
    public void setNIP(int urutanMasuk) {
        this.nip = "M" + urutanMasuk;
    }
}
