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
    public class TKController : ControllerBase
    {
        private TKservice tkservice;
        public TKController(TKservice tkservice)
        {
            this.tkservice = tkservice;
        }
        [HttpGet("get-tk")]
        public async Task<ActionResult> GetTK()
        {
            return Ok(new { status = true, message1 = "", data = tkservice.GetTKList() });
        }

        [HttpPost("insert-tk")]
        public async Task<ActionResult> InsertTK(TKModel tk)
        {
            TK t = new TK();
            t.taikhoan = tk.taikhoan;
            t.matkhau = tk.matkhau;
            t.quyen = tk.quyen;
            t.ten = tk.ten;
            t.sdt = tk.sdt;
            t.tien = tk.tien;
            t.vitri = tk.vitri;
            tkservice.InsertTK(t);
            return Ok(new { status = true, message = "success" });
        }
        [HttpPatch("update-tk")]
        public async Task<ActionResult> UpdateTK(TKModel tk)
        {
            
            TK t = new TK();
            t.taikhoan = tk.taikhoan;
            t.matkhau = tk.matkhau;
            t.quyen = tk.quyen;
            t.ten = tk.ten;
            t.sdt = tk.sdt;
            t.tien = tk.tien;
            t.vitri = tk.vitri;
            tkservice.UpdateTK(t);
            return Ok(new { status = true, message = "success" });
        }
        [HttpDelete("delete-tk")]
        public async Task<ActionResult> DeleteTK(TKModel tk)
        {
            TK t = new TK();
            t.taikhoan = tk.taikhoan;
            tkservice.DeleteTK(t);
            return Ok(new { status = true, message = "success" });
        }
    }
}
