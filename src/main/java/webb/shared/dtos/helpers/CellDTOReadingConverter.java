package webb.shared.dtos.helpers;

import com.mongodb.BasicDBList;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import webb.shared.dtos.puzzle.CellDTO;

import java.util.ArrayList;

@ReadingConverter
public class CellDTOReadingConverter implements Converter<ArrayList, CellDTO> {
    @Override
    public CellDTO convert(ArrayList dbList) {
        int row = (int) dbList.get(0);
        int col = (int) dbList.get(1);
        return new CellDTO(new int[] { row, col });
    }
}