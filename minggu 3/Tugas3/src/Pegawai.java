public class Pegawai {
    String nama;
    int umur;
    double gaji;
    int lamaKerja;

    public Pegawai(String nama, int umur, double gaji, int lamaKerja) {
        this.nama = nama;
        this.umur = umur;
        this.gaji = gaji;
        this.lamaKerja = lamaKerja;
    }

    public double getGaji() {
        return gaji;
    }

    public String getNama() {
        return nama;
    }
}

class Direktur extends Pegawai {
    private static final double BONUS_DIREKTUR = 5000000;

    public Direktur(String nama, int umur, double gajiDasar, int lamaKerja) {
        super(nama, umur, gajiDasar * lamaKerja + BONUS_DIREKTUR, lamaKerja);
    }
}

class Manager extends Pegawai {
    public Manager(String nama, int umur, double gajiDasar, int lamaKerja) {
        super(nama, umur, gajiDasar * lamaKerja * 1.5, lamaKerja);
    }
}

class Supervisor extends Pegawai {
    public Supervisor(String nama, int umur, double gajiDasar, int lamaKerja) {
        super(nama, umur, gajiDasar * lamaKerja * 1.2, lamaKerja);
    }
}

class Operasional extends Pegawai {
    public Operasional(String nama, int umur, double gajiDasar, int lamaKerja) {
        super(nama, umur, gajiDasar * lamaKerja, lamaKerja);
    }
}
