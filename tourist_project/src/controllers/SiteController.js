const { query } = require('../config/db/postgres');

class SiteController {
    async index(req,res,next){
        try {
            const result = await query('SELECT $1::text as message', ['Hello from PostgreSQL!']);
            res.render('home', { message: result.rows[0].message });
        } catch (err) {
            console.error('Error fetching data:', err);
            res.status(500).send('Internal Server Error');
        }
    }
}

module.exports = new SiteController();  