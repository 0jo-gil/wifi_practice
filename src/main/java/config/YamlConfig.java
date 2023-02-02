package config;

import config.dto.Api;
import config.dto.Db;

public class YamlConfig {
    private Db db;
    private Api api;

    public YamlConfig() {
    }

    public Db getDb() {
        return this.db;
    }

    public Api getApi() {
        return this.api;
    }

    public void setDb(Db db) {
        this.db = db;
    }

    public void setApi(Api api) {
        this.api = api;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof YamlConfig)) return false;
        final YamlConfig other = (YamlConfig) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$db = this.getDb();
        final Object other$db = other.getDb();
        if (this$db == null ? other$db != null : !this$db.equals(other$db))
            return false;
        final Object this$api = this.getApi();
        final Object other$api = other.getApi();
        if (this$api == null ? other$api != null : !this$api.equals(other$api))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof YamlConfig;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $db = this.getDb();
        result = result * PRIME + ($db == null ? 43 : $db.hashCode());
        final Object $api = this.getApi();
        result = result * PRIME + ($api == null ? 43 : $api.hashCode());
        return result;
    }

    public String toString() {
        return "YamlConfig(db=" + this.getDb() + ", api=" + this.getApi() + ")";
    }
}