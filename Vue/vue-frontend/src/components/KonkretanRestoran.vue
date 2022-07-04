<template>
    <div class = "container">
        <h1 class = "text-center">{{this.naziv}}</h1>
        <table class="tabela-prikaz">
            <thead>
                <th>Naziv</th>
                <th>Tip</th>
                <th>Adresa</th>
            </thead>
            <tbody>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        name: "KonkretanRestoran",

        data() {
            return{
                naziv : "",
                tip : "",
                adresa : "",
                ocena : "",
                komentari: [],
                artikli :[]
            }
        },

        methods: {
            restoranPrikaz(){
               /* const response = await axios.get("/restoran/" + this.$route.params.id, {
                    naziv : this.naziv,
                    tip : this.tip,
                    adresa : this.adresa,
                    ocena : this.ocena,
                    komentari: this.komentari,
                    artikli : this.artikli
                }); */

                axios.get("http://localhost:8081/restoran/" + this.id).then((response) =>{
                    this.naziv = response.data.naziv;
                    this.tip = response.data.tip;
                    this.adresa = response.lokacija.adresa;
                    this.ocena = response.data.ocena;
                    this.komentari = response.data.komentari;
                    this.artikli = response.data.artikli;
                }); 
           }
        },

        created(){
            this.restoranPrikaz()
        },
        
    }
</script>

<style scoped>

</style>
