public class Pegawai {
    protected String nip;
    protected String nama;
    protected double gaji;
    protected int cuti = 12;

    public Pegawai(String nama, double gaji) {
        this.nama = nama;
        this.gaji = gaji;
    }

    public double hitungTHR() {
        return gaji;
    }

    public void tambahCuti(int hari) {
        this.cuti += hari;
    }

    public void tambahCuti(String tipeCuti, String gender) {
        if (tipeCuti.equalsIgnoreCase("pernikahan")) {
            this.cuti += 2;
        } else if (tipeCuti.equalsIgnoreCase("persalinan")) {
            if (gender.equalsIgnoreCase("wanita")) {
                this.cuti += 90;
            } else if (gender.equalsIgnoreCase("pria")) {
                this.cuti += 3;
            }
        }
    }

    public void setNIP(String nipInput) {
        if (nipInput.startsWith("P")) {
            this.nip = nipInput;
        } else {
            System.out.println("Format NIP tidak sesuai untuk Pegawai.");
        }
    }

    public void setNIP(int urutanMasuk) {
        this.nip = "P" + urutanMasuk;
    }

    public void tampilkanInfo() {
        System.out.println("Nama: " + nama);
        System.out.println("NIP: " + nip);
        System.out.println("Gaji: " + gaji);
        System.out.println("Cuti: " + cuti + " hari");
        System.out.println("THR: " + hitungTHR());
    }
}
