using Lib.Data;
using Lib.Entity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lib.Repositories
{

    public interface IPHIEUTHUEXERepository : IRepository<PHIEUTHUEXE>
    {
        List<PHIEUTHUEXE> GetPHIEUTHUEXEList();
    }
    public class PHIEUTHUEXERepository : RepositoryBase<PHIEUTHUEXE>, IPHIEUTHUEXERepository
    {
        public PHIEUTHUEXERepository(ApplicationDbContext dbContext) : base(dbContext)
        {

        }
        public List<PHIEUTHUEXE> GetPHIEUTHUEXEList()
        {
            var query = _dbcontext.PHIEUTHUEXE.AsQueryable();
            return query.ToList();
        }
    }
}
