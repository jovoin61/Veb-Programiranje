<template>
    <form class="form-horizontal" @submit.prevent="handleSubmit">
        <h3>DODAVANJE RESTORANA</h3>

        <div class="form-group">
            <label class="control-label col-sm-2">Naziv</label>
            <input type="text" v-model="naziv" placeholder="naziv"/>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2">Tip</label>
            <input type="text" v-model="tip" placeholder="tip"/>
        </div>

        <button type="submit">DODAJ RESTORAN</button>
    </form>
</template>

<script>
    import axios from 'axios'

    export default {
        name: 'DodavanjeRestorana',

        data(){
            return {
                naziv : "",
                tip : "",
            }
        },

        methods : {
            async handleSubmit(){
                const response = await axios.post('http://localhost:8081/admin/dodavanje/restoran', {
                    naziv : this.naziv,
                    tip : this.tip,
                });

                console.log(response);

                this.$router.push('/admin')
            }
        }
    }
</script>