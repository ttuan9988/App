package com.example.lib.Model.XETHUE;

import com.example.lib.Model.TK.TKModel;

import java.io.Serializable;
import java.util.List;

public class XETHUEModel implements Serializable
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
        private String vitrixe;

        private String trangthai;

        private String maphieu;

        private String biensoxe;

        private String loaixe;

        private String xe;

        private String giaxe;

        private String kiemdinh;

        private String maxe;

        private String hinhanh;

        private String tinhtrang;

        private String hanthaynhot;

        private String tenxe;

        public String getVitrixe ()
        {
            return vitrixe;
        }

        public void setVitrixe (String vitrixe)
        {
            this.vitrixe = vitrixe;
        }

        public String getTrangthai ()
        {
            return trangthai;
        }

        public void setTrangthai (String trangthai)
        {
            this.trangthai = trangthai;
        }

        public String getMaphieu ()
        {
            return maphieu;
        }

        public void setMaphieu (String maphieu)
        {
            this.maphieu = maphieu;
        }

        public String getBiensoxe ()
        {
            return biensoxe;
        }

        public void setBiensoxe (String biensoxe)
        {
            this.biensoxe = biensoxe;
        }

        public String getLoaixe ()
        {
            return loaixe;
        }

        public void setLoaixe (String loaixe)
        {
            this.loaixe = loaixe;
        }

        public String getXe ()
        {
            return xe;
        }

        public void setXe (String xe)
        {
            this.xe = xe;
        }

        public String getGiaxe ()
        {
            return giaxe;
        }

        public void setGiaxe (String giaxe)
        {
            this.giaxe = giaxe;
        }

        public String getKiemdinh ()
        {
            return kiemdinh;
        }

        public void setKiemdinh (String kiemdinh)
        {
            this.kiemdinh = kiemdinh;
        }

        public String getMaxe ()
        {
            return maxe;
        }

        public void setMaxe (String maxe)
        {
            this.maxe = maxe;
        }

        public String getHinhanh ()
        {
            return hinhanh;
        }

        public void setHinhanh (String hinhanh)
        {
            this.hinhanh = hinhanh;
        }

        public String getTinhtrang ()
        {
            return tinhtrang;
        }

        public void setTinhtrang (String tinhtrang)
        {
            this.tinhtrang = tinhtrang;
        }

        public String getHanthaynhot ()
        {
            return hanthaynhot;
        }

        public void setHanthaynhot (String hanthaynhot)
        {
            this.hanthaynhot = hanthaynhot;
        }

        public String getTenxe ()
        {
            return tenxe;
        }

        public void setTenxe (String tenxe)
        {
            this.tenxe = tenxe;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [vitrixe = "+vitrixe+", trangthai = "+trangthai+", maphieu = "+maphieu+", biensoxe = "+biensoxe+", loaixe = "+loaixe+", xe = "+xe+", giaxe = "+giaxe+", kiemdinh = "+kiemdinh+", maxe = "+maxe+", hinhanh = "+hinhanh+", tinhtrang = "+tinhtrang+", hanthaynhot = "+hanthaynhot+", tenxe = "+tenxe+"]";
        }
    }

}

