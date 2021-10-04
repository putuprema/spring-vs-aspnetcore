using Eater.Interfaces;
using Microsoft.EntityFrameworkCore;

namespace Eater.Repositories
{
    public class BaseRepository<T> : IBaseRepository<T> where T : class
    {
        protected readonly AppDbContext _context;

        public BaseRepository(AppDbContext context)
        {
            _context = context;
        }

        public void Delete(T entity) => _context.Remove(entity);

        public ValueTask<T?> GetById(object id) => _context.FindAsync<T>(id);

        public Task<List<T>> GetAll() => Set().ToListAsync();

        public T Save(T entity)
        {
            _context.Add(entity);
            return entity;
        }

        public Task SaveChangesAsync() => _context.SaveChangesAsync();

        public DbSet<T> Set() => _context.Set<T>();

        public T Update(T entity)
        {
            _context.Update(entity);
            return entity;
        }
    }
}
