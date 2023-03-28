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
    public class XETHUEController : ControllerBase
    {
        private XETHUEservice xethueservice;
        public XETHUEController(XETHUEservice xethueservice)
        {
            this.xethueservice = xethueservice;
        }
        [HttpGet("get-xethue")]
        public async Task<ActionResult> GetXETHUE()
        {
            return Ok(new { status = true, message1 = "", data = xethueservice.GetXETHUEList() });
        }
        [HttpPost("insert-xethue")]
        public async Task<ActionResult> InsertXETHUE(XETHUEModel xethue)
        {
            XETHUE xt = new XETHUE();
            xt.tenxe = xethue.tenxe;
            xt.loaixe = xethue.loaixe;
            xt.biensoxe = xethue.biensoxe;
            xt.giaxe = xethue.giaxe;
            xt.hinhanh = xethue.hinhanh;
            xt.tinhtrang = xethue.tinhtrang;
            xt.maphieu = xethue.maphieu;
            xt.xe = xethue.xe;
            xt.trangthai = xethue.trangthai;
            xt.kiemdinh = xethue.kiemdinh;
            xt.hanthaynhot = xethue.hanthaynhot;
            xt.vitrixe = xethue.vitrixe;
            xethueservice.InsertXETHUE(xt);
            return Ok(new { status = true, message = "success" });
        }
        [HttpPatch("update-xethue")]
        public async Task<ActionResult> UpdateXETHUE(XETHUEModel xethue)
        {
            XETHUE xt = new XETHUE();
            xt.maxe = xethue.maxe;
            xt.tenxe = xethue.tenxe;
            xt.loaixe = xethue.loaixe;
            xt.biensoxe = xethue.biensoxe;
            xt.giaxe = xethue.giaxe;
            xt.hinhanh = xethue.hinhanh;
            xt.tinhtrang = xethue.tinhtrang;
            xt.maphieu = xethue.maphieu;
            xt.xe = xethue.xe;
            xt.trangthai = xethue.trangthai;
            xt.kiemdinh = xethue.kiemdinh;
            xt.hanthaynhot = xethue.hanthaynhot;
            xt.vitrixe = xethue.vitrixe;
            xethueservice.UpdateXETHUE(xt);
            return Ok(new { status = true, message = "success" });
        }
        [HttpDelete("delete-xethue")]
        public async Task<ActionResult> DeleteXETHUE(XETHUEModel xethue)
        {
            XETHUE xt = new XETHUE();
            xt.maxe = xethue.maxe;
           xethueservice.DeleteXETHUE(xt);
            return Ok(new { status = true, message = "success" });
        }
    }
}
