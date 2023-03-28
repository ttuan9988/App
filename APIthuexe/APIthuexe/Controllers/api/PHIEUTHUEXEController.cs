using APIthuexe.Models;
using Lib.Entity;
using Lib.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;
namespace APIthuexe.Controllers.api
{

    [Route("api/[controller]")]
    [ApiController]
    public class PHIEUTHUEXEController : ControllerBase
    {
        private PHIEUTHUEXEservice phieuthuexeservice;
        public PHIEUTHUEXEController(PHIEUTHUEXEservice phieuthuexeservice)
        {
            this.phieuthuexeservice = phieuthuexeservice;
        }
        [HttpGet("get-phieuthuexe")]
        public async Task<ActionResult> GetPHIEUTHUEXE()
        {
            return Ok(new { status = true, message1 = "", data = phieuthuexeservice.GetPHIEUTHUEXEList() });
        }
        [HttpPost("insert-phieuthuexe")]
        public async Task<ActionResult> InsertPHIEUTHUEXE(PHIEUTHUEXEModel phieuthuexe)
        {
            PHIEUTHUEXE ptx = new PHIEUTHUEXE();
            ptx.ngaythue = phieuthuexe.ngaythue;
            ptx.ngaytra = phieuthuexe.ngaytra;
            ptx.tiencoc = phieuthuexe.tiencoc;
            ptx.taikhoan = phieuthuexe.taikhoan;
            ptx.biensoxe = phieuthuexe.biensoxe;
            ptx.banglai = phieuthuexe.banglai;
            ptx.duyet = phieuthuexe.duyet;
            ptx.thoigian = phieuthuexe.thoigian;
            ptx.tienxe = phieuthuexe.tienxe;
            ptx.xem = phieuthuexe.xem;
            ptx.mahd = phieuthuexe.mahd;
            phieuthuexeservice.InsertPHIEUTHUEXE(ptx);
            return Ok(new { status = true, message = "success" });
        }
        [HttpPatch("update-phieuthuexe")]
        public async Task<ActionResult> UpdatePHIEUTHUEXE(PHIEUTHUEXEModel phieuthuexe)
        {
            PHIEUTHUEXE ptx = new PHIEUTHUEXE();
            ptx.maphieu = phieuthuexe.maphieu;
            ptx.ngaythue = phieuthuexe.ngaythue;
            ptx.ngaytra = phieuthuexe.ngaytra;
            ptx.tiencoc = phieuthuexe.tiencoc;
            ptx.taikhoan = phieuthuexe.taikhoan;
            ptx.biensoxe = phieuthuexe.biensoxe;
            ptx.banglai = phieuthuexe.banglai;
            ptx.duyet = phieuthuexe.duyet;
            ptx.thoigian = phieuthuexe.thoigian;
            ptx.tienxe = phieuthuexe.tienxe;
            ptx.xem = phieuthuexe.xem;
            phieuthuexeservice.UpdatePHIEUTHUEXE(ptx);
            return Ok(new { status = true, message = "success" });
        }
        [HttpDelete("delete-phieuthuexe")]
        public async Task<ActionResult> DeletePHIEUTHUEXE(PHIEUTHUEXEModel phieuthuexe)
        {
            PHIEUTHUEXE ptx = new PHIEUTHUEXE();
            ptx.maphieu = phieuthuexe.maphieu;
            phieuthuexeservice.DeletePHIEUTHUEXE(ptx);
            return Ok(new { status = true, message = "success" });
        }
    }
}
