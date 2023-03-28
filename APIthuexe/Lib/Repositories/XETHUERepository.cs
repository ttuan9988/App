using Lib.Data;
using Lib.Entity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lib.Repositories
{
    public interface IXETHUERepository : IRepository<XETHUE>
    {
        List<XETHUE> GetXETHUEList();
    }
    public class XETHUERepository : RepositoryBase<XETHUE>, IXETHUERepository
    {
        public XETHUERepository(ApplicationDbContext dbContext) : base(dbContext)
        {

        }
        public List<XETHUE> GetXETHUEList()
        {
            var query = _dbcontext.XETHUE.AsQueryable();
            return query.ToList();
        }
    }
}
