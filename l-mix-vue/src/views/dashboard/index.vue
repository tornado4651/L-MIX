<template>
  <div class="dashboard-container">
    <div class="dashboard-text">登录名: {{ user.username }}</div>
    <div class="dashboard-text">昵称: {{ user.nickname }}</div>
    <div class="dashboard-text">性别: {{ user.gender }}</div>
    <div class="dashboard-text">出生年月: {{ user.birthday }}</div>
    <div class="dashboard-text">电话: {{ user.telephone }}</div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'user'
    ])
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
