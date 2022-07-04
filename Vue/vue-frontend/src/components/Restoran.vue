<template>
    <div class = "container">
        <h1 class = "text-center">RESTORANI</h1>
        <table class="tabela-prikaz">
            <thead>
                <th>Naziv</th>
                <th>Tip</th>
                <th>Adresa</th>
            </thead>
            <tbody>
                <tr v-for = "restoran in restorani" v-bind:key="restoran.id">
                    <td>{{restoran.naziv}}</td>
                    <td>{{restoran.tip}}</td>
                    <td>{{restoran.lokacija.adresa}}</td>
                    <button @click = "this.$router.push('/restoran/${restoran.naziv}')"></button>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    import RestoranService from '@/services/RestoranService'

    export default {
        name: 'RestoraniIspis',
        data(){
            return{
                restorani : []
            }
        },
        methods:{
            getRestorani(){
                RestoranService.getRestorani().then((response) =>{
                    this.restorani = response.data;
                    console.log(response);
                });
            }
        },
        created(){
            this.getRestorani()
        }
    }
</script>

<style>

.container {
  margin-top: auto;
  width: 100%;
}

.text-center {
    padding-top: 50px;
    margin-left: auto;
    margin-right: auto;
}

.tabela-prikaz{
    margin-left: auto;
    margin-right: auto;
}

</style>