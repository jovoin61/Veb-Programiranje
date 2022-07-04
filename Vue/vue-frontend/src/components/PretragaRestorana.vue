<template>
  <form class="form-horizontal" @submit.prevent="handleSubmit">
    <h3>PRETRAGA RESTORANA</h3>

    <div class="form-group">
      <label class="control-label col-sm-2">Naziv</label>
      <input type="text" v-model="naziv" placeholder="naziv" />
    </div>

    <div class="form-group">
      <label class="control-label col-sm-2">Tip</label>
      <input type="text" v-model="tip" placeholder="tip" />
    </div>

    <div class="form-group">
      <label class="control-label col-sm-2">Adresa</label>
      <input type="text" v-model="lokacija" placeholder="adresa" />
    </div>

    <button type="submit">PRETRAZITE</button>
  </form>
</template>

<script>
import axios from "axios";

export default {
  name: "PretragaRestorana",

  data() {
    return {
      restorani: [],
      naziv: "",
      tip: "",
      lokacija: "",
    };
  },

  methods: {
    async handleSubmit() {
      const response = await axios
        .get("http://localhost:8081/restoran/pretraga", {
          data: {
            naziv: this.naziv,
            tip: this.tip,
            lokacija: this.lokacija,
          },
        })
        .then((response) => {
          this.restorani = response.data;
        })
        .catch((err) => {
          alert(err);
          console.log(err);
        });

      console.log(response);

      //this.$router.push('/admin')
    },
  },
};
</script>