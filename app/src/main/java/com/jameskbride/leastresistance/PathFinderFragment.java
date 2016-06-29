package com.jameskbride.leastresistance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jameskbride.leastResistance.PathFinder;
import com.jameskbride.leastResistance.PathResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PathFinderFragment extends Fragment {

    public static final String ROW_SPLITTER_REGEX = "\n";
    public static final String COLUMN_SPLITTER_REGEX = " ";
    @BindView(R.id.map_text)
    EditText mapText;

    @BindView(R.id.find_path_button)
    Button findPathButton;

    @BindView(R.id.path_text)
    TextView pathText;

    private Unbinder unbinder;

    public static PathFinderFragment newInstance() {
        return new PathFinderFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_least_resistance, container, false);
        this.unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.find_path_button)
    public void findPath() {
        try {
            int[][] map = extractMap();
            PathFinder pathFinder = new PathFinder();

            PathResult pathResult = pathFinder.findPath(map);

            pathText.setText(pathResult.toString());
        } catch (Exception e) {
            pathText.setText(R.string.create_valid_map_error_text);
        }
    }

    private int[][] extractMap() {
        String[] mapRows = mapText.getText().toString().split(ROW_SPLITTER_REGEX);
        int columnCount = mapRows[0].split(" ").length;

        int[][] map = new int[mapRows.length][columnCount];
        for (int rowIndex = 0; rowIndex < mapRows.length; rowIndex++) {
            String[] columns = mapRows[rowIndex].split(COLUMN_SPLITTER_REGEX);
            int[] columnIntValues = new int[columnCount];
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                columnIntValues[columnIndex] = Integer.parseInt(columns[columnIndex]);
            }

            map[rowIndex] = columnIntValues;
        }
        return map;
    }
}
