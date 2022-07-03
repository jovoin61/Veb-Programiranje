import axios from 'axios'

const RESTORANI_PRIKAZ_URL = 'http://localhost:8081/'

class RestoranService{
    getRestorani(){
        return axios.get(RESTORANI_PRIKAZ_URL);
    }
}

export default new RestoranService()