package com.example.lib.Model.PHIEUTHUEXE;

import com.example.lib.Model.TK.TKModel;
import com.example.lib.Model.XETHUE.XETHUEModel;

import java.io.Serializable;
import java.util.List;

public class PHIEUTHUEXEModel implements Serializable
{
    private List<Data> data;

    private String status;

    private String message1;

    public List<Data> getData ()
    {
        return data;
    }

    public void setData (List<Data> data)
    {
        this.data = data;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getMessage1 ()
    {
        return message1;
    }

    public void setMessage1 (String message1)
    {
        this.message1 = message1;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", status = "+status+", message1 = "+message1+"]";
    }
    public class Data
    {
        private String xem;

        private String ngaytra;

        private String tienxe;

        private String mahd;

        private String maphieu;

        private String taikhoan;

        private String biensoxe;

        private String tiencoc;

        private String banglai;

        private String ngaythue;

        private String duyet;

        private String thoigian;

        public String getXem ()
        {
            return xem;
        }

        public void setXem (String xem)
        {
            this.xem = xem;
        }

        public String getNgaytra ()
        {
            return ngaytra;
        }

        public void setNgaytra (String ngaytra)
        {
            this.ngaytra = ngaytra;
        }

        public String getTienxe ()
        {
            return tienxe;
        }

        public void setTienxe (String tienxe)
        {
            this.tienxe = tienxe;
        }

        public String getMahd ()
    {
        return mahd;
    }

        public void setMahd (String mahd)
        {
            this.mahd = mahd;
        }

        public String getMaphieu ()
        {
            return maphieu;
        }

        public void setMaphieu (String maphieu)
        {
            this.maphieu = maphieu;
        }

        public String getTaikhoan ()
        {
            return taikhoan;
        }

        public void setTaikhoan (String taikhoan)
        {
            this.taikhoan = taikhoan;
        }

        public String getBiensoxe ()
        {
            return biensoxe;
        }

        public void setBiensoxe (String biensoxe)
        {
            this.biensoxe = biensoxe;
        }

        public String getTiencoc ()
        {
            return tiencoc;
        }

        public void setTiencoc (String tiencoc)
        {
            this.tiencoc = tiencoc;
        }

        public String getBanglai ()
        {
            return banglai;
        }

        public void setBanglai (String banglai)
        {
            this.banglai = banglai;
        }

        public String getNgaythue ()
        {
            return ngaythue;
        }

        public void setNgaythue (String ngaythue)
        {
            this.ngaythue = ngaythue;
        }

        public String getDuyet ()
        {
            return duyet;
        }

        public void setDuyet (String duyet)
        {
            this.duyet = duyet;
        }

        public String getThoigian ()
        {
            return thoigian;
        }

        public void setThoigian (String thoigian)
        {
            this.thoigian = thoigian;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [xem = "+xem+", ngaytra = "+ngaytra+", tienxe = "+tienxe+", mahd = "+mahd+", maphieu = "+maphieu+", taikhoan = "+taikhoan+", biensoxe = "+biensoxe+", tiencoc = "+tiencoc+", banglai = "+banglai+", ngaythue = "+ngaythue+", duyet = "+duyet+", thoigian = "+thoigian+"]";
        }
    }



}