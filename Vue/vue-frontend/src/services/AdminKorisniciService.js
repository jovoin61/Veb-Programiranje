import axios from 'axios'

const ADMIN_KORISNICI_URL = 'http://localhost:8081/admin/korisnici'

class AdminKorisniciService{
    getKorisnici(){
        return axios.get(ADMIN_KORISNICI_URL);
    }
}

export default new AdminKorisniciService()