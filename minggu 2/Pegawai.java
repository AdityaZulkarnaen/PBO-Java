public class Pegawai {
    private String nama;
    private int umur;
    private double gaji;
    
    private static int jumlahPegawai = 0;
    
    public Pegawai(String nama, int umur, double gaji) {
        this.nama = nama;
        this.umur = umur;
        this.gaji = gaji;
        jumlahPegawai++; 
    }
    
    public void tampilkanInfo() {
        System.out.println("Pegawai " + nama + " berumur " + umur + " memiliki gaji sebesar " + gaji);
    }
    
    public static int getJumlahPegawai() {
        return jumlahPegawai;
    }
    
    public static void main(String[] args) {
        Pegawai pegawai1 = new Pegawai("Budi", 30, 5000000);
        Pegawai pegawai2 = new Pegawai("Ani", 28, 5500000);
        Pegawai pegawai3 = new Pegawai("Dodi", 35, 6000000);
        
        pegawai1.tampilkanInfo();
        pegawai2.tampilkanInfo();
        pegawai3.tampilkanInfo();
        
        System.out.println("Jumlah pegawai yang telah dibuat: " + Pegawai.getJumlahPegawai());
    }
}