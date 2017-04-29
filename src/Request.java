/**
 * Created by apple on 29.04.17.
 * В запросе обязаны присутствовать параметры ticker и period, а также хотя бы один
 * из параметров from, to или bars (комбинация from, to или bars при этом может быть
 * произвольной), в противном случае возвращается ошибка Absent parameter. Пустые
 * параметры запроса игнорируются (считаются отсутствующими).
 * <p/>
 * Если входные данные корректны, сервер возвращает список свеч, удовлетворяющих запросу,
 * отсортированные по дате и времени свечи по убыванию. Если указан параметр bars, то
 * возвращается указанной в нем число свеч, в противном случае возвращается не более 1000 свеч.
 * Если указан параметр from и не указан параметр to, то возвращаются свечи, «поджатые» ко
 * времени начала указанного в запросе периода (from), во всех остальных случаях — ко времени
 * окончания указанного в запросе периода (если указан параметр to — ко времени to, если не
 * указан — к текущему серверному времени).
 * <p/>
 * Возвращаются следующие поля свеч: DATE, OPEN, HIGH, LOW, CLOSE, VOLUME.
 * Формат поля DATE — Y-m-d H:i:s, разделителем для остальных полей является точка (.).
 * Поля записи разделены табуляцией (\t), записи разделены символом новой строки (\n).
 * После последней записи также присутствует символ новой строки (\n).
 */
public class Request {

    private String host = "history.alor.ru";
    private Board board;
    private Ticker ticker;
    private int period;     // 1, 5, 10, 15, 20, 30, 60, 1440;
    private String from;      // Y-m-d или дата и время в формате Y-m-d H:i:s
    private String to;
    private int bars = 0;       //максимальное количество свеч, которое нужно получить (не менее 1 и не более 1000)

    private void PrivateConstructor(Board board, Ticker ticker, int period) {
        this.board = board;
        this.ticker = ticker;
        this.period = period;
        // TODO check for correctly entered of ticker and period
    }

    public Request(Board board, Ticker ticker, int period, String from) {
        this.PrivateConstructor(board, ticker, period);
        this.from = from;
    }

    public Request(Board board, Ticker ticker, int period, String from, String to) {
        this(board, ticker, period, from);
        this.to = to;
    }

    public Request(Board board, Ticker ticker, int period, String from, String to, int bars) {
        this(board, ticker, period, from, to);
        this.bars = bars;
    }

    public Request(Board board, Ticker ticker, int period, int bars) {
        this.PrivateConstructor(board, ticker, period);
        this.bars = bars;
    }

    public String getRequest() {
        return "GET /?board=" + board.toString() +
                "&ticker=" + ticker.toString() +
                "&period=" + parseParam(period) +
                "&from=" + parseParam(from) +
                "&to=" + parseParam(to) +
                "&bars=" + parseParam(bars) +
                " HTTP/1.1\n" +
                "Host: history.alor.ru\n";
    }

    private String parseParam(String param) {
        return (param == null) ? "" : param;
    }

    private String parseParam(int param) {
        return (param == 0) ? "" : Integer.toString(param);
    }

    public String getHost() {
        return host;
    }
}

enum Board {
    MICEX,
    FORTS
}

enum Ticker {
    SBER,
    GAZP,
    ROSN,
    BANE,
    TATN,
    VTBR
}