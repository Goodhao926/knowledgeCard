package club.goodhao.object;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonGoogleTranslate  {

    @SerializedName("sentences")
    private List<SentencesDTO> sentences;
    @SerializedName("src")
    private String src;
    @SerializedName("confidence")
    private Double confidence;

    @Override
    public String toString() {
        return "JsonGoogleTranslate{" +
                "sentences=" + sentences +
                ", src='" + src + '\'' +
                ", confidence=" + confidence +
                ", spell=" + spell +
                ", ldResult=" + ldResult +
                '}';
    }

    @SerializedName("spell")
    private SpellDTO spell;
    @SerializedName("ld_result")
    private LdResultDTO ldResult;

    public List<SentencesDTO> getSentences() {
        return sentences;
    }

    public void setSentences(List<SentencesDTO> sentences) {
        this.sentences = sentences;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public SpellDTO getSpell() {
        return spell;
    }

    public void setSpell(SpellDTO spell) {
        this.spell = spell;
    }

    public LdResultDTO getLdResult() {
        return ldResult;
    }

    public void setLdResult(LdResultDTO ldResult) {
        this.ldResult = ldResult;
    }

    public static class SpellDTO {
    }

    public static class LdResultDTO {
        @SerializedName("srclangs")
        private List<String> srclangs;

        @Override
        public String toString() {
            return "LdResultDTO{" +
                    "srclangs=" + srclangs +
                    ", srclangsConfidences=" + srclangsConfidences +
                    ", extendedSrclangs=" + extendedSrclangs +
                    '}';
        }

        @SerializedName("srclangs_confidences")
        private List<Double> srclangsConfidences;
        @SerializedName("extended_srclangs")
        private List<String> extendedSrclangs;

        public List<String> getSrclangs() {
            return srclangs;
        }

        public void setSrclangs(List<String> srclangs) {
            this.srclangs = srclangs;
        }

        public List<Double> getSrclangsConfidences() {
            return srclangsConfidences;
        }

        public void setSrclangsConfidences(List<Double> srclangsConfidences) {
            this.srclangsConfidences = srclangsConfidences;
        }

        public List<String> getExtendedSrclangs() {
            return extendedSrclangs;
        }

        public void setExtendedSrclangs(List<String> extendedSrclangs) {
            this.extendedSrclangs = extendedSrclangs;
        }
    }

    public static class SentencesDTO {
        @SerializedName("trans")
        private String trans;
        @SerializedName("orig")
        private String orig;
        @SerializedName("backend")
        private Integer backend;

        @Override
        public String toString() {
            return "SentencesDTO{" +
                    "trans='" + trans + '\'' +
                    ", orig='" + orig + '\'' +
                    ", backend=" + backend +
                    '}';
        }

        public String getTrans() {
            return trans;
        }

        public void setTrans(String trans) {
            this.trans = trans;
        }

        public String getOrig() {
            return orig;
        }

        public void setOrig(String orig) {
            this.orig = orig;
        }

        public Integer getBackend() {
            return backend;
        }

        public void setBackend(Integer backend) {
            this.backend = backend;
        }
    }
}
