package com.example.lib.Model.PHIEUTHUEXE;

public class PHIEUTHUEXECallBackModel {
    private String message;

    private String status;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message )
    {
        this.message = "success";
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = "true";
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", status = "+status+"]";
    }
}
