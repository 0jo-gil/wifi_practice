package config.dto;


public class Api {
    private String url;
    private String name;
    private String key;
    private String type;
    private String encoding;

    public Api() {
    }

    public String getUrl() {
        return this.url;
    }

    public String getName() {
        return this.name;
    }

    public String getKey() {
        return this.key;
    }

    public String getType() {
        return this.type;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Api)) return false;
        final Api other = (Api) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$url = this.getUrl();
        final Object other$url = other.getUrl();
        if (this$url == null ? other$url != null : !this$url.equals(other$url))
            return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name))
            return false;
        final Object this$key = this.getKey();
        final Object other$key = other.getKey();
        if (this$key == null ? other$key != null : !this$key.equals(other$key))
            return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type))
            return false;
        final Object this$encoding = this.getEncoding();
        final Object other$encoding = other.getEncoding();
        if (this$encoding == null ? other$encoding != null : !this$encoding.equals(other$encoding))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Api;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $url = this.getUrl();
        result = result * PRIME + ($url == null ? 43 : $url.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $key = this.getKey();
        result = result * PRIME + ($key == null ? 43 : $key.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $encoding = this.getEncoding();
        result = result * PRIME + ($encoding == null ? 43 : $encoding.hashCode());
        return result;
    }

    public String toString() {
        return "Api(url=" + this.getUrl() + ", name=" + this.getName() + ", key=" + this.getKey() + ", type=" + this.getType() + ", encoding=" + this.getEncoding() + ")";
    }
}
